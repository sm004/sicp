(defn last-pair [l]
  (cond
    (nil? l) (throw (Exception. "last-pair called with empty list"))
    (nil? (next l)) l
    :else (last-pair (next l))))

(println (last-pair (list 23 72 149 34)))
