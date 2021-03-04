(ns pomo.views
  (:require [reagent.core :as r]
            [re-frame.core :refer [subscribe dispatch] :as re]
            [clojure.string :as str :refer [trim split join]]))

(defn minutes-left [seconds]
  (Math/floor (/ seconds 60)))

(defn seconds-left [seconds]
  (rem seconds 60))

(defn time-left [seconds]
  (str (minutes-left seconds) ":" (seconds-left seconds)))

(defn timer []
  (let [progress @(re/subscribe [:time-left])
        timer-started @(re/subscribe [:timer-activation])
        timer-update @(re/subscribe [:timer-update])
        button-text (if timer-started "Stop" "Start")]
    [:div
     [:div.timer (time-left progress)]
     [:button.timer-toggle {:class "pure-button"
                            :on-click #(re/dispatch [:timer-toggle timer-started])} button-text]]))

(defn main-layout []
  [:div.pure-g
   [:div.pure-u-1-3]
   [:div.pure-u-1-3
    [:h1 "The Pomo App"]
    [timer]]])

(defn pomo-app [] [main-layout])
