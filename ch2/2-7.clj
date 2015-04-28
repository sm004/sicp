(defn make-interval [l u] [l u])

(defn lower-bound [i] (first i))
(defn upper-bound [i] (second i))

(defn add-interval [x y]
  (make-interval (+ (lower-bound x) (lower-bound y))
                 (+ (upper-bound x) (upper-bound y))))

(defn mul-interval [x y]
  (let
    [p1 (* (lower-bound x) (lower-bound y))
     p2 (* (lower-bound x) (upper-bound y))
     p3 (* (upper-bound x) (lower-bound y))
     p4 (* (upper-bound x) (upper-bound y))]
    (make-interval (min p1 p2 p3 p4)
                   (max p1 p2 p3 p4))))

(defn div-interval [x y]
  (mul-interval x
                (make-interval (/ 1.0 (upper-bound y))
                               (/ 1.0 (lower-bound y)))))

(def r1 (make-interval (- 6.8 0.68) (+ 6.8 0.68)))
(def r2 (make-interval (- 4.7 0.235) (+ 4.7 0.235)))
(def one (make-interval 1.0 1.0))

(println (div-interval
           one
           (add-interval
             (div-interval one r1)
             (div-interval one r2))))
