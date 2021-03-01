(ns pomo.views
  (:require [reagent.core :as reagent]
            [re-frame.core :refer [subscribe dispatch]]
            [clojure.string :as str :refer [trim split join]]))

(defn timer [progress]
  [:div
   [:div.timer progress]
   [:button.go "Start"]])

(defn pomo-app []
  (let [progress "00:00"]
    [timer progress]))
