(ns cljs
  (:require [com.widdindustries.tiado-cljs2 :as util]
            [shadow.cljs.build-report :as build-report]))

(defn test-watch []
  (util/browser-test-build :watch {}))

(defn app-config [modules]
  (->
    (util/browser-app-config)
    (merge {:modules modules
            :build-id  (-> modules keys first)})))

(defn app-release [modules]
  (util/prod-build
    (-> (app-config modules)
        (dissoc :devtools)
        (merge
          {:output-dir "firebase/public"
           :compiler-options {:warnings {:fn-deprecated false}}
           :js-options
           {:resolve {"@js-joda/core" {:target :global :global "JSJoda"}
                      "react"         {:target :global :global "React"}
                      "react-dom"     {:target :global :global "ReactDOM"}}}}))))

(defn app-watch [modules]
  (util/watch (app-config modules)))

(def java-time {:java-time {:entries ['time-lib-comparison.java-time]}})
(def js-date {:js-date {:entries ['time-lib-comparison.js-date]}})

(comment
  (build-report/-main :js-date "js-date-report.html")
  
  ; start compiling and watching the two apps
  (app-watch java-time)
  (app-watch js-date)
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

  ; do the release
  (app-release java-time)
  (app-release js-date)


  )
