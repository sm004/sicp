(def us-coins (list 50 25 10 5 1))
(def uk-coins (list 100 50 20 10 5 2 1 0.5))

(defn first-denomination [l] (first l))
(defn except-first-denomination [l] (next l))
(defn no-more? [l] (nil? l))

(defn cc [amount coin-values]
  (cond (= amount 0) 1
        (or (< amount 0) (no-more? coin-values)) 0
        :else
         (+ (cc amount
                (except-first-denomination coin-values))
            (cc (- amount
                   (first-denomination coin-values))
                coin-values))))

(println (cc 100 us-coins))
;; answer is same if denominations are unique
(println (cc 100 (reverse us-coins)))
