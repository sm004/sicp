(defn power [b e]
  (defn helper [a b e]
    (cond (= e 0) a
          (even? e) (helper a (* b b) (quot e 2))
          (odd? e) (helper (* a b) b (- e 1))))
  (helper 1 b e))

(defn max-power-divides [b]
  (fn helper [x]
    (if (not= 0 (rem x b)) 0
        (+ 1 (helper (quot x b))))))

(defn arithmetic-cons [a b] (* (power 2 a) (power 3 b)))
(def arithmetic-car (max-power-divides 2))
(def arithmetic-cdr (max-power-divides 3))

(def p (arithmetic-cons 4 5))
(println (arithmetic-car p))
(println (arithmetic-cdr p))
