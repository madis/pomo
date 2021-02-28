(ns pomo.app
  (:require [re-frame.core :refer [dispatch-sync]]
            [reagent.core :as reagent]
            [reagent.dom :as rdom]
            [pomo.events]  ;; These three are only
            [pomo.subs]    ;; required to make the compiler
            [pomo.views])) ;; load them

(defn ^:export init
  []
  (dispatch-sync [:initialise-db])

  (println "Initializing pomo.app")
  (rdom/render [pomo.views/pomo-app] (.getElementById js/document "app")))
