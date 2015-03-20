(define (fast-expt-iter b n)
  (define (helper a b k)
    (cond ((= k 0) a)
	  ((even? k) (helper a (* b b) (/ k 2)))
	  (else (helper (* a b) b (- k 1)))))
  (helper 1 b n))

(fast-expt-iter 2 16)
(fast-expt-iter 3 8)
