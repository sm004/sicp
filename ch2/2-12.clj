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

(def r1 (make-interval (- 6.8 0.68) (+ 6.8 0.68)))
(def r2 (make-interval (- 4.7 0.235) (+ 4.7 0.235)))
(def one (make-interval 1.0 1.0))
(println r1)
(println (make-center-percent 6.8 10))
(println [(center r1) (percent r1)])
(println r2)
(println (make-center-percent 4.7 5))
(println [(center r2) (percent r2)])
