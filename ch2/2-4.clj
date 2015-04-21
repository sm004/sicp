(defn procedural-cons [x y]
  (fn [m] (m x y)))

(defn procedural-car [z]
  (z (fn [p q] p)))

(defn procedural-cdr [z]
  (z (fn [p q] q)))

(def p (procedural-cons 4 5))
(println (procedural-car p))
(println (procedural-cdr p))
