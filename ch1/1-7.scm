(define (sqrt-iter guess x)
  (if (good-enough? guess x)
      guess
      (sqrt-iter (improve guess x)
                 x)))

(define (improve guess x)
  (average guess (/ x guess)))

(define (average x y)
  (/ (+ x y) 2))

;; change good-enough? to use relative error
(define (good-enough? guess x)
  (< (/ (abs (- (square guess) x)) x) 0.001))

(define (sqrt x)
  (sqrt-iter 1.0 x))

(sqrt 1e-100)
