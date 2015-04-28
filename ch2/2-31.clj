(defn square [x] (* x x))

(defn tree-map [f tree]
  (map
   (fn [x] (if (list? x) (tree-map f x) (f x)))
   tree))

(defn square-tree [tree] (tree-map square tree))

(def tree
     (list 1
           (list 2 (list 3 4) 5)
           (list 6 7)))
(println (square-tree tree))
