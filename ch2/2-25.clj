(def l1 '(1 3 (5 7) 9))

(def l2 '((7)))

(def l3 '(1 (2 (3 (4 (5 (6 7)))))))

(defn compose [& procs]
  (defn helper [procs]
    (if (nil? procs)
        (fn [x] x)
        (fn [x] ((first procs) ((helper (next procs)) x)))))
  (helper procs))

(println ((compose first next first next next) l1))
(println ((compose first first) l2))
(println ((compose first next first next first next first next first next first next) l3))
