(ns user)

(defn dev []
  (require 'cljs)
  (eval '(cljs/start-live-compilation))
  )

;(dev)
