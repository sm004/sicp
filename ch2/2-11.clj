(defn make-interval [l u] [l u])

(defn lower-bound [i] (first i))
(defn upper-bound [i] (second i))

(defn add-interval [x y]
  (make-interval (+ (lower-bound x) (lower-bound y))
                 (+ (upper-bound x) (upper-bound y))))

(defn mul-interval-old [x y]
  (let
    [p1 (* (lower-bound x) (lower-bound y))
     p2 (* (lower-bound x) (upper-bound y))
     p3 (* (upper-bound x) (lower-bound y))
     p4 (* (upper-bound x) (upper-bound y))]
    (make-interval (min p1 p2 p3 p4)
                   (max p1 p2 p3 p4))))

;; 1. (2, 4) * (2, 4) = (4, 16)
;; 2. (2, 4) * (-2, 4) = (-8, 16)
;; 3. (2, 4) * (-2, -1) = (-8, -2)
;; 4. (-2, 4) * (2, 4) = case 2
;; 5. (-2, 4) * (-2, 4) = requires 4 multiplications
;; 6. (-2, 4) * (-2, -1) = (-8, 4)
;; 7. (-2, -1) * (2, 4) = case 3
;; 8. (-2, -1) * (-2, 4) = case 6
;; 9. (-2, -1) * (-2, -1) = (1, 4)

(defn mul-interval [x y]
  (let [x1 (lower-bound x)
        x2 (upper-bound x)
        y1 (lower-bound y)
        y2 (upper-bound y)]
       (cond
         ;; cases 1, 2 and 3
         (> x1 0)
         (cond
           ;; case 1
           (> y1 0)
           (make-interval (* x1 y1) (* x2 y2))
           ;; case 2
           (and (< y1 0) (> y2 0))
           (make-interval (* x2 y1) (* x2 y2))
           ;; case 3
           :else
           (make-interval (* x2 y1) (* x1 y2)))
         ;; cases 4, 5 and 6
         (and (< x1 0) (> x2 0))
         (cond
           ;; case 4
           (> y1 0)
           (mul-interval y x)
           ;; case 5
           (and (< y1 0) (> y2 0))
           (make-interval (min (* x1 y2) (* x2 y1))
                          (max (* x1 y1) (* x2 y2)))
           ;; case 6
           :else
           (make-interval (* x2 y1) (* x1 y1)))
         ;; cases 7, 8 and 9
         :else
         (cond
           ;; case 7
           (> y1 0)
           (mul-interval y x)
           ;; case 8
           (and (< y1 0) (> y2 0))
           (mul-interval y x)
           ;; case 9
           :else
           (make-interval (* x2 y2) (* x1 y1))))))

(defn div-interval [x y]
  (if
    (and (< (lower-bound y) 0) (> (upper-bound y) 0))
    (throw (Exception. "cannot divide by interval spanning zero"))
    (mul-interval x
                  (make-interval (/ 1.0 (upper-bound y))
                                 (/ 1.0 (lower-bound y))))))

(def r1 (make-interval (- 6.8 0.68) (+ 6.8 0.68)))
(def r2 (make-interval (- 4.7 0.235) (+ 4.7 0.235)))
(def one (make-interval 1.0 1.0))

(println (div-interval
           one
           (add-interval
             (div-interval one r1)
             (div-interval one r2))))

;; (def zero (make-interval -0.5 0.5))
;; (println (div-interval r1 zero))

(def x (make-interval 2 4))
(def y (make-interval -2 4))
(def z (make-interval -2 -1))
(println [(mul-interval x x) "\t" (mul-interval-old x x)])
(println [(mul-interval x y) "\t" (mul-interval-old x y)])
(println [(mul-interval x z) "\t" (mul-interval-old x z)])
(println [(mul-interval y x) "\t" (mul-interval-old y x)])
(println [(mul-interval y y) "\t" (mul-interval-old y y)])
(println [(mul-interval y z) "\t" (mul-interval-old y z)])
(println [(mul-interval z x) "\t" (mul-interval-old z x)])
(println [(mul-interval z y) "\t" (mul-interval-old z y)])
(println [(mul-interval z z) "\t\t" (mul-interval-old z z)])
