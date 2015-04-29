(defn memq [item x]
  (cond (nil? x) false
        (= item (first x)) x
        :else (memq item (next x))))

(println (list 'a 'b 'c))
(println (list (list 'george)))
(println (next '((x1 x2) (y1 y2))))
(println (fnext '((x1 x2) (y1 y2))))
(println (list? (first '(a short list))))
(println (memq 'red '((red shoes) (blue socks))))
(println (memq 'red '(red shoes blue socks)))
