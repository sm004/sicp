(define (cbrt-iter guess x)
  (if (good-enough? guess x)
      guess
      (cbrt-iter (improve guess x)
                 x)))

(define (improve y x)
  (/ (+ (/ x (* y y))
	(* 2 y))
     3))

(define (cube x) (* x x x))

;; change good-enough? to use relative error
(define (good-enough? guess x)
  (< (/ (abs (- (cube guess) x)) x) 0.001))

(define (cbrt x)
  (cbrt-iter 1.0 x))

(cbrt 1e-90)
