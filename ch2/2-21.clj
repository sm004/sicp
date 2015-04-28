(defn square [x] (* x x))

(defn square-list-1 [items]
  (if (nil? items)
      nil
      (cons (square (first items)) (square-list-1 (next items)))))

(defn square-list-2 [items]
  (map square items))

(println (square-list-1 (list 1 2 3 4)))
(println (square-list-2 (list 1 2 3 4)))
