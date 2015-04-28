(defn reverse [l]
  (defn helper [x acc]
    (if (nil? x)
        acc
        (helper (next x) (cons (first x) acc))))
  (helper l nil))

(println (reverse (list 1 4 9 16 25)))
