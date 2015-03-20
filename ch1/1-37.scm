(define (cont-frac n d k)
  (define (iter i result)
    (if (= i 0)
	result
	(iter (- i 1) (/ (n i)
			 (+ (d i) result)))))
  (iter k 0))

(define (phi-inv-approx k)
  (cont-frac (lambda (i) 1.0)
	     (lambda (i) 1.0)
	     k))

(phi-inv-approx 100)

(define (cont-frac-recur n d k)
  (define (helper i)
    (if (> i k)
	0
	(/ (n i)
	   (+ (d i) (helper (+ i 1))))))
  (helper 1))

(define (phi-inv-approx-recur k)
  (cont-frac-recur (lambda (i) 1.0)
		   (lambda (i) 1.0)
		   k))

(phi-inv-approx-recur 100)

