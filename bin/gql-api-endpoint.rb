#!/usr/bin/env ruby

require 'json'
require 'webrick' # `gem install webrick`
require 'sinatra' # `gem install sinatra`
require 'sinatra/reloader' # `gem install sinatra-contrib`

set :bind, '0.0.0.0'
set :port, 4567

def sample_graphql
  {
    "data": {
      "sanity": true,
      "user": {
        "user_address": "0xc238fa6ccc9d226e2c49644b36914611319fc3ff",
        "user_cart": {
          "items": [
            {"title": "First item", "price": 420.69}
          ]
        }
      }
    }
  }
end

get '/' do
  content_type :json
  sample_graphql.to_json
end
