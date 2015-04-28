(defn accumulate [op i s]
  (if (nil? s)
      i
      (op (first s)
          (accumulate op i (next s)))))

(defn map [p s]
  (accumulate (fn [x y] (cons (p x) y)) nil s))
(defn append [seq1 seq2]
  (accumulate cons seq2 seq1))
(defn length [sequence]
  (accumulate (fn [x y] (+ 1 y)) 0 sequence))

(def s (range 1 6))
(println s)
(println (map (fn [x] (* x x)) s))
(println (append s (range 6 11)))
(println (length s))
