(ns compilation
  (:require [shadow.cljs.devtools.api :as api]
            [shadow.cljs.devtools.server :as server]
            [shadow.cljs.build-report :as build-report]
            [clojure.java.shell :as sh]))

(defn restart-server []
  (server/stop!)
  (server/start!))

(defn clean [path]
  (sh/sh "rm" "-rf" (str "./firebase/public/" path)))

(defn delete-cache []
  (sh/sh "rm" "-rf" (str "./.shadow-cljs/builds")))

#_(defn devcards []
  (api/stop-worker :app-dev)
  (api/stop-worker :devcards)
  (clean "devcards/js")
  (api/watch :devcards {:verbose true}))

(defn prod-build [build-name]
  (api/stop-worker :js-date-dev)
  (api/stop-worker :java-time-dev)
  (clean (name build-name))
  (api/release build-name))

(defn watch [build-name]
  (api/stop-worker :js-date-dev)
  (api/stop-worker :java-time-dev)
  (clean (name build-name))
  (api/watch build-name {:verbose false}))

(defn start-live-compilation [build-name]
  (restart-server)
  (watch build-name))

(defn cljs-repl [build-name]
  (api/repl build-name))

(comment
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