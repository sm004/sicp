(defn same-parity [x & xs]
  (defn helper [xs]
    (cond (nil? xs)
          nil
          (= (even? x) (even? (first xs)))
          (cons (first xs) (helper (next xs)))
          :else
          (helper (next xs))))
  (cons x (helper xs)))

(println (same-parity 1 2 3 4 5 6 7))
(println (same-parity 2 3 4 5 6 7))
