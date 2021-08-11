(ns time-lib-comparison.app-main
  (:require [reagent.dom :as rdom]
            [time-lib-comparison.view :as view]))

(defn app-container [id]
  (js/document.getElementById id))

(defn mount-components [interval-calc tomorrow-str]
  (when-let [container (app-container "app")]
    (rdom/render [view/app-view interval-calc tomorrow-str] container)))

(defn init [interval-calc tomorrow-str]
  (.addEventListener
    js/window
    "load"
    (fn []
      (js/console.log "Initializing")
      (mount-components interval-calc tomorrow-str))))


