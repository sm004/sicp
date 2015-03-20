;; a bit of cheating with the let*
(define (expmod base exp m)
  (cond ((= exp 0) 1)
        ((even? exp)
	 (let* ((k (expmod base (/ exp 2) m))
		(l (remainder (square k) m)))
	   (if (and (= l 1) (not (or (= k 1) (= k (- m 1)))))
	       0
	       l)))
        (else
         (remainder (* base (expmod base (- exp 1) m))
                    m))))

(define (try-it a n)
  (= (expmod a (- n 1) n) 1))

(define (miller-rabin-test-all n)
  (define (helper k)
    (cond ((= k n) true)
	  ((try-it k n) (helper (+ k 1)))
	  (else false)))
  (helper 2))

(map miller-rabin-test-all '(561 1105 1729 2465 2821 6601))
