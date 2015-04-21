(defn zero [f]
  (fn [x] x))

(defn add-1 [n]
  (fn [f]
    (fn [x] (f ((n f) x)))))

(defn f [x] (+ x 1))
(def one (add-1 zero))
(def two (add-1 one))
(def three (add-1 two))

(println ((zero f) 0))
(println ((one f) 0))
(println ((two f) 0))
(println ((three f) 0))

(defn plus [m n]
  (fn [f]
    (fn [x] ((m f) ((n f) x)))))

(println (((plus three three) f) 0))

(defn mult [m n]
  (fn [f]
    (fn [x] ((m (n f)) x))))

(println (((mult three three) f) 0))

(defn power [m n]
  (fn [f]
    (fn [x] (((n m) f) x))))

(println (((power three (power two three)) f) 0))
(println (((power zero zero) f) 0))
