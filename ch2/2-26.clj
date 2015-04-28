(def x (list 1 2 3))
(def y (list 4 5 6))

(defn append [x y]
  (if (nil? x)
      y
      (cons (first x) (append (next x) y))))

(println (append x y))
(println (cons x y))
(println (list x y))
