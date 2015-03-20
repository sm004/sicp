(define (expmod base exp m)
  (cond ((= exp 0) 1)
        ((even? exp)
         (remainder (square (expmod base (/ exp 2) m))
                    m))
        (else
         (remainder (* base (expmod base (- exp 1) m))
                    m))))

(define (try-it a n)
  (= (expmod a n n) a))

(define (fermat-test-all n)
  (define (helper k)
    (cond ((= k n) true)
	  ((try-it k n) (helper (+ k 1)))
	  (else false)))
  (helper 2))

(map fermat-test-all '(561 1105 1729 2465 2821 6601))
