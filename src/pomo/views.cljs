(ns pomo.views
  (:require [reagent.core :as r]
            [re-frame.core :refer [subscribe dispatch] :as re]
            [clojure.string :as str :refer [trim split join]]
            [swiper :as swiper]
            ["swiper/core" :refer [Navigation Pagination] :default SwiperCore]))

(defn stick
  "Assigns key to browser window.zeStick property for inspection and debugging"
  [val]
  (println "Setting window.zeStick to " val)
  (set! (.. js/window -zeStick) val))

(defn setup-swiper []
  (println "Setting up swiper")
  (. SwiperCore use [Navigation Pagination]))

(def swiper-config
  {:direction "vertical"
   :loop true
   :pagination {:el ".swiper-pagination"}
   :navigation {:prevEl ".swiper-button-prev"
                :nextEl ".swiper-button-next"}
   :scrollbar {:el ".swiper-scrollbar"}})

(defn start-swiper []
  (println "Starting swiper, for real")
  (new swiper/Swiper ".swiper-container" (clj->js swiper-config)))

(def swiper-instance (atom nil))
(defn init-page []
  (setup-swiper)
  (reset! swiper-instance (start-swiper)))

(defn swiper-demo []
  [:div.swiper-container
   [:div.swiper-wrapper
    [:div.swiper-slide "First"]
    [:div.swiper-slide "Second"]
    [:div.swiper-slide "Third"]]
   [:div.swiper-pagination]
   [:div.swiper-button-prev]
   [:div.swiper-button-next]
   [:div.swiper-scrollbar]])

(defn timer []
  (let [progress "10:00"
        timer-started @(re/subscribe [:timer-activation])
        button-text (if timer-started "Stop" "Start")]
    [:div
     [:div.timer progress]
     [:button.timer-toggle {:class "pure-button"
                            :on-click #(re/dispatch [:timer-toggle timer-started])} button-text]]))
(defn main-layout []
  [:div.pure-g
   [:div.pure-u-1-3]
   [:div.pure-u-1-3
    [:h1 "The Pomo App"]
    [timer]
    [swiper-demo]]])

(defn pomo-app [] [main-layout])
