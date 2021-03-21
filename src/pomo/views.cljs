(ns pomo.views
  (:require [reagent.core :as r]
            [re-frame.core :refer [subscribe dispatch] :as re]
            [district.ui.graphql.subs :as gql]
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

(def query-as-vector-full
  {:queries [[:user {:user/id 123}
              [:user/address
               [:user/cart-items [:cart-item/quantity
                                  [:cart-item/item [:item/title
                                                    :item/price]]]]]]]})

(def query-as-vector
  {:queries [[:user {:user/id 123} [:user/address]]]})

(def query-as-string
  ; "query ($id: ID!) { sanity user(user_id: $id) { user_address user_cartItems { cartItem_item { item_title item_price } } } }"
  "query ($id: ID!) { sanity user(user_id: $id) { user_address } }"
  )

(defn gql-show []
  (let [
        query (atom {})
        _ (println "-----> 1. Going for a query")
        ; query (subscribe [::gql/query "query { sanity }"])
        query (subscribe [::gql/query query-as-string {:variables {:id 123}}])
        ; query (subscribe [::gql/query query-as-vector])
        _ (println "-----> 2. Got the query back" @query)
        ]
    (fn []
      (if (:graphql/loading? @query)
        [:div "Loading..."]
        (let [{:keys [:user/address :user/cart-items]} @query]
          [:div
           [:div
            [:div "Everything (the @query atom)"]
            [:pre (str @query)]]
           [:div "User address:" address]
           [:div "Cart items:" cart-items]])))))

(defn main-layout []
  [:div.pure-g
   [:div.pure-u-1-3]
   [:div.pure-u-1-3
    [:h1 "The Pomo App"]
    [timer]
    [gql-show]
    ]])

(defn pomo-app [] [main-layout])
