(defn deep-reverse [items]
  (defn helper [items acc]
    (cond (nil? items) acc
          (list? (first items)) (helper
                                  (next items)
                                  (cons 
                                    (deep-reverse (first items))
                                    acc))
          :else (helper (next items) (cons (first items) acc))))
  (helper items nil))

(def x (list (list 1 2) (list 3 4)))

(println (reverse x))
(println (deep-reverse x))
