(def x-point first)
(def y-point second)

(defn make-point [x y] [x y])

(def start-segment first)
(def end-segment second)

(defn make-segment [x y] [x y])

(defn midpoint-segment [s]
  (let [p1 (start-segment s)
        p2 (end-segment s)
        x1 (x-point p1)
        x2 (x-point p2)
        y1 (y-point p1)
        y2 (y-point p2)]
    (make-point (/ (+ x1 x2) 2)
                (/ (+ y1 y2) 2))))
    
(defn print-point [p]
  (print "(")
  (print (x-point p))
  (print ",")
  (print (y-point p))
  (print ")")
  (println))

(def s (make-segment (make-point 0 0)
                     (make-point 3 5)))

(print-point (midpoint-segment s))
