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

(def s '((1 2 3) (4 5 6) (7 8 9) (10 11 12)))

(println (accumulate-n + 0 s))
