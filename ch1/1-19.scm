(define (fib n)
  (fib-iter 1 0 0 1 n))
(define (fib-iter a b p q count)
  (cond ((= count 0) b)
        ((even? count)
         (fib-iter a
                   b
                   (+ (* q q) (* p p))
                   (+ (* q q) (* 2 p q))
                   (/ count 2)))
        (else (fib-iter (+ (* b q) (* a q) (* a p))
                        (+ (* b p) (* a q))
                        p
                        q
                        (- count 1)))))

;; a <- bq + aq + ap
;; b <- bp + aq
;; a2 <- (bp + aq)q + (bq + aq + ap)q + (bq + aq + ap)p
;; a2 <- b(pq + qq + qp) + a(qq + qq + pq + pq + pp)
;; q2 = qq + 2pq
;; p2 = qq + pp

(map fib '(0 1 2 3 4 5 6 7 8 9))
