(ns cljs
  (:require [com.widdindustries.tiado-cljs2 :as util]))

(defn test-watch []
  (util/browser-test-build :watch {}))

(defn app-release []
   {:npm-deps {:install false}

      :target-defaults
      {:browser
       {:compiler-options {:warnings {:fn-deprecated false}}
        :js-options
        {:resolve {"@js-joda/core" {:target :global :global "JSJoda"}
                   "react"         {:target :global :global "React"}
                   "react-dom"     {:target :global :global "ReactDOM"}
                   }}}}}
  )

(defn app-watch [modules]
  (util/watch
    (->
      (util/browser-app-config)
      (merge {:modules modules
              :build-id  (-> modules keys first)}))))

(comment
  ; start compiling and watching the two apps
  (app-watch {:java-time {:entries ['time-lib-comparison.java-time]}})
  (app-watch {:js-date {:entries ['time-lib-comparison.js-date]}})
  ; visit e.g. http://localhost:9000/java-time.html or http://localhost:9000/js-date.html
  (util/repl :java-time)
  (util/repl :js-date)
  
  (util/stop-server)
  ; start up live-compilation of tests
  (test-watch)
  ; run cljs tests, having opened browser at test page (see print output of above "for tests, open...")
  (util/run-tests)
  ; start a cljs repl session in the test build. :cljs/quit to exit
  (util/repl :browser-test-build)
  ; run tests in headless browser
  (util/compile-and-run-tests-headless* :release)

  (util/stop-server)

  )


#_(defn clean [path]
  (sh/sh "rm" "-rf" (str "./firebase/public/" path)))

#_(defn delete-cache []
  (sh/sh "rm" "-rf" (str "./.shadow-cljs/builds")))

#_(defn devcards []
  (api/stop-worker :app-dev)
  (api/stop-worker :devcards)
  (clean "devcards/js")
  (api/watch :devcards {:verbose true}))

#_(defn prod-build [build-name]
  (api/stop-worker :js-date-dev)
  (api/stop-worker :java-time-dev)
  (clean (name build-name))
  (api/release build-name))

#_(defn watch [build-name]
  (api/stop-worker :js-date-dev)
  (api/stop-worker :java-time-dev)
  (clean (name build-name))
  (api/watch build-name {:verbose false}))


#_(comment
  (restart-server)

  ;;; js-date build
  (watch :js-date-dev)
  ; visit http://localhost:8083/js-date.html
  (cljs-repl :js-date-dev)
  :cljs/quit

(prod-build :js-date)
(build-report/-main :js-date "js-date-report.html")

;;; java-time build
(watch :java-time-dev)
; http://localhost:8083/js-date.html
(cljs-repl :java-time-dev)
:cljs/quit

(prod-build :java-time)
(build-report/-main :java-time "java-time-report.html")

  )