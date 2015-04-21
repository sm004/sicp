(def x-point first)
(def y-point second)

(defn make-point [x y] [x y])

(def ll-rect first)
(def ur-rect second)

(defn sidex-rect [r]
  (- (x-point (ur-rect r))
     (x-point (ll-rect r))))
(defn sidey-rect [r]
  (- (y-point (ur-rect r))
     (y-point (ll-rect r))))

(defn perimeter-rect [r]
  (* 2 (+ (sidex-rect r) (sidey-rect r))))
(defn area-rect [r]
  (* (sidex-rect r) (sidey-rect r)))

(defn make-rect [p1 p2]
  (let [x1 (x-point p1)
        x2 (x-point p2)
        y1 (y-point p1)
        y2 (y-point p2)
        xsort (if (< x1 x2) [x1 x2] [x2 x1])
        ysort (if (< y1 y2) [y1 y2] [y2 y1])]
    [(make-point (first xsort) (first ysort))
     (make-point (second xsort) (second ysort))]))

(defn print-pair [acc1 acc2 printfn]
  (fn [p]
    (print "(")
    (printfn (acc1 p))
    (print ",")
    (printfn (acc2 p))
    (print ")")))

(def print-point (print-pair x-point y-point print))
(def print-rect (print-pair ll-rect ur-rect print-point))

(def p1 (make-point 4 3))
(def p2 (make-point 2 7))
(def r (make-rect p1 p2))

(print-rect r)
(println)
(println (area-rect r))
(println (perimeter-rect r))
