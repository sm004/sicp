(defn accumulate [op i s]
  (if (nil? s)
      i
      (op (first s)
          (accumulate op i (next s)))))

(defn horner-eval [x coefficient-sequence]
  (accumulate (fn [this-coeff higher-terms]
                (+ this-coeff (* x higher-terms)))
              0
              coefficient-sequence))

(println (horner-eval 2 (list 1 3 0 5 0 1)))
