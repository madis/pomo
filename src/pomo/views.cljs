(ns pomo.views
  (:require [reagent.core :as r]
            [re-frame.core :refer [subscribe dispatch] :as re]
            [clojure.string :as str :refer [trim split join]]))

(defn timer []
  (let [progress "10:00"
        timer-started @(re/subscribe [:timer-activation])
        button-text (if timer-started "Stop" "Start")]

    (println "Things are" timer-started button-text)
    [:div
     [:div.timer progress]
     [:button.timer-toggle {:class "pure-button"
                            :on-click #(re/dispatch [:timer-toggle timer-started])} button-text]]))

(defn main-layout []
  [:div.pure-g
   [:div.pure-u-1-3]
   [:div.pure-u-1-3
    [:h1 "The Pomo App"]
    [timer]]])

(defn pomo-app [] [main-layout])
