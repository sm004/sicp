(define (f-recur n)
  (if (< n 3)
      n
      (+ (f-recur (- n 1))
	 (* 2 (f-recur (- n 2)))
	 (* 3 (f-recur (- n 3))))))

(define (f-iter n)
  (define (f-iter-helper k f1 f2 f3)
    (if (= k (+ n 1))
	f1
	(f-iter-helper (+ k 1)
		       (+ f1 (* 2 f2) (* 3 f3))
		       f1
		       f2)))
  (if (< n 3)
      n
      (f-iter-helper 3 2 1 0)))

(f-recur 5)

(f-iter 5)
