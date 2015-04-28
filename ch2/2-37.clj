(defn accumulate [op i s]
  (if (nil? s)
      i
      (op (first s)
          (accumulate op i (next s)))))

(defn accumulate-n [op init seqs]
  (if (nil? (first seqs))
      nil
      (cons (accumulate op init (map first seqs))
            (accumulate-n op init (map next seqs)))))

(defn dot-product [v w]
  (accumulate + 0 (map * v w)))

(defn matrix-*-vector [m v]
  (map (fn [r] (dot-product r v)) m))

(defn transpose [mat]
  (accumulate-n cons nil mat))

(defn matrix-*-matrix [m n]
  (let
    [cols (transpose n)]
    (map
     (fn [r] (map (fn [c] (dot-product r c)) cols))
     m)))

(def m '((1 2 3 4) (4 5 6 6) (6 7 8 9)))
(def v '(1 1 1 1))
(def o '((1 1 1) (1 1 1) (1 1 1)))

(println (dot-product v v))
(println (matrix-*-vector m v))
(println (transpose m))
(println (matrix-*-matrix o o))
