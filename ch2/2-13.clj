(defn make-interval [l u] [l u])

(defn lower-bound [i] (first i))
(defn upper-bound [i] (second i))

(defn make-center-width [c w]
  (make-interval (- c w) (+ c w)))

(defn center [i]
  (/ (+ (lower-bound i) (upper-bound i)) 2))
(defn width [i]
  (/ (- (upper-bound i) (lower-bound i)) 2))

(defn make-center-percent [c p]
  (make-center-width c (/ (* c p) 100)))

(defn percent [i]
  (* (/ (width i) (center i)) 100))

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
  (if
    (and (< (lower-bound y) 0) (> (upper-bound y) 0))
    (throw (Exception. "cannot divide by interval spanning zero"))
    (mul-interval x
                  (make-interval (/ 1.0 (upper-bound y))
                                 (/ 1.0 (lower-bound y))))))

(defn par1 [r1 r2]
  (div-interval (mul-interval r1 r2)
                (add-interval r1 r2)))
(defn par2 [r1 r2]
  (let [one (make-interval 1 1)]
    (div-interval one
                  (add-interval (div-interval one r1)
                                (div-interval one r2)))))

(def r1 (make-center-percent 6.8 10))
(def r2 (make-center-percent 4.7 5))

(println (par1 r1 r2))
(println (par2 r1 r2))
