(define (smallest-divisor n)
  (find-divisor n 2))
(define (find-divisor n test-divisor)
  (cond ((> (square test-divisor) n) n)
        ((divides? test-divisor n) test-divisor)
        (else (find-divisor n (+ test-divisor 1)))))
(define (divides? a b)
  (= (remainder b a) 0))

(define (prime? n)
  (= n (smallest-divisor n)))

(define (timed-prime-test n)
  (newline)
  (display n)
  (start-prime-test n (runtime)))
(define (start-prime-test n start-time)
  (if (prime? n)
      (report-prime (- (runtime) start-time))))
(define (report-prime elapsed-time)
  (display " *** ")
  (display elapsed-time))

(define (search-for-primes lo hi)
  (cond ((> lo hi) (display "end"))
	((even? lo) (search-for-primes (+ lo 1) hi))
	(else (timed-prime-test lo)
	      (search-for-primes (+ lo 2) hi))))

(search-for-primes 1000 1020)
;; 1009, 1013, 1019; time 0
(search-for-primes 10000 10040)
;; 10007, 10009, 10037; time 0
(search-for-primes 100000 100050)
;; 100003, 100019, 100043; time 0
(search-for-primes 1000000 1000040)
;; 1000003, 1000033, 1000037; time 0

;; the following numbers are from the community schemewiki
(timed-prime-test 100000000057)
;; 0.64
(timed-prime-test 1000000000063)
;; 2.02
