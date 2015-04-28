(defn fold-right [op i s]
  (if (nil? s)
      i
      (op (first s) (fold-right op i (next s)))))

(defn fold-left [op i s]
  (if (nil? s)
      i
      (fold-left op (op i (first s)) (next s))))

(defn append [s1 s2]
  (fold-right cons s2 s1))

(defn reverse-left [s]
  (fold-left (fn [x y] (cons y x)) nil s))

(defn reverse-right [s]
  (fold-right (fn [x y] (append y (list x))) nil s))

(def s (range 1 11))
(println (reverse-left s))
(println (reverse-right s))
