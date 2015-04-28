(defn append [x y]
  (if (nil? x)
      y
      (cons (first x) (append (next x) y))))

(defn subsets [s]
  (if (nil? s)
      (list nil)
      (let
        [rest (subsets (next s))]
        (append rest (map
                      (fn [x] (cons (first s) x))
                      rest)))))

(println (subsets '(1 2 3)))
