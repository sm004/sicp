(defn accumulate [op i s]
  (if (nil? s)
      i
      (op (first s)
          (accumulate op i (next s)))))

(defn count-leaves [t]
  (accumulate (fn [x y] (+ y x))
              0
              (map
               (fn [x] (if (list? x) (count-leaves x) 1))
               t)))

(def t (list 1 2 (list 3 4 5 6 (list 7 8) (list 9 10))))
(println (count-leaves t))
