(defn append [x y]
  (if (nil? x)
      y
      (cons (first x) (append (next x) y))))

(defn fringe [items]
  (cond (nil? items) nil
        (list? (first items)) (append (fringe (first items))
                                      (fringe (next items)))
        :else (cons (first items) (fringe (next items)))))

(def x (list (list 1 2) (list 3 4)))
(println (fringe x))
(println (fringe (list x x)))
