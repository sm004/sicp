(defn equal? [s1 s2]
  (cond (and (list? s1) (list? s2))
        (cond (and (list? (first s1)) (list? (first s2)))
              (and (equal? (first s1) (first s2))
                   (equal? (next s1) (next s2)))
              (and (not (list? (first s1))) (not (list? (first s2))))
              (and (= (first s1) (first s2))
                   (equal? (next s1) (next s2)))
              :else false)
        (and (not (list? s1)) (not (list? s2)))
        (= s1 s2)
        :else false))

(println (equal? '(this is a list) '(this is a list)))
(println (equal? '(this is a list) '(this (is a) list)))