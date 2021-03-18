(ns pomo.app
  (:require [re-frame.core :refer [dispatch-sync]]
            [pomo.graphql]
            [cljsjs.apollo-fetch]
            [cljsjs.dataloader]
            [cljsjs.graphql]
            [district.ui.graphql]
            [reagent.core :as reagent]
            [reagent.dom :as rdom]
            [mount.core :refer [defstate] :as mount]
            [pomo.events]  ;; These three are only
            [pomo.subs]    ;; required to make the compiler
            [pomo.views])) ;; load them

(defonce ze-state (atom {}))

(defstate app-core
  :start (do
           (println "Starting mount component in pomo.app")
           (reset! ze-state {:state "Started"}))
  :stop (do
          (println "Stopping mount component in pomo.app")
          (reset! ze-state {:state "Stop"})))

(defn start-mount []
  (-> (mount/with-args {:graphql {:schema pomo.graphql/schema :url "http://localhost:4567"}})
      mount/start))

(defn ^:dev/before-load stop []
  (mount/stop)
  (js/console.log "before-load stop"))

(defn ^:dev/after-load start []
  (js/console.log "after-load start")
  (start-mount)
  (rdom/force-update-all))

(defn ^:export init
  []
  (println "Initializing pomo.app")
  (dispatch-sync [:initialise-db])
  (start-mount)
  (rdom/render [pomo.views/pomo-app] (.getElementById js/document "app")))
