(defn square [x] (* x x))

(defn square-tree-1 [tree]
  (cond (nil? tree) nil
        (list? (first tree)) (cons (square-tree-1 (first tree))
                                   (square-tree-1 (next tree)))
        :else (cons (square (first tree))
                    (square-tree-1 (next tree)))))

(defn square-tree-2 [tree]
  (map
   (fn [x] (if (list? x) (square-tree-2 x) (square x)))
   tree))

(def tree
     (list 1
           (list 2 (list 3 4) 5)
           (list 6 7)))
(println (square-tree-1 tree))
(println (square-tree-2 tree))
