(defn for-each [proc items]
  (defn sequential [s1 s2] s1 s2)
  (if (nil? items)
      ()
      (sequential (proc (first items))
                  (for-each proc (next items)))))

(for-each println '(57 321 88))
