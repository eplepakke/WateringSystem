package ru.newtonbox.wateringsystem.mock

const val MOCK_PLANT_LIST = "{\n" +
        "\t\"paginator\": null, \n" +
        "\t\"page_obj\": null, \n" +
        "\t\"is_paginated\": false, \n" +
        "\t\"plantinfo_list\": [\n" +
        "\t\t{\n" +
        "\t\t\t\"pump_state_id\": 5, \n" +
        "\t\t\t\"pump_state\": {\n" +
        "\t\t\t\t\"id\": 5, \n" +
        "\t\t\t\t\"channel\": \"base1/device1/pump_state\"\n" +
        "\t\t\t}, \n" +
        "\t\t\t\"soil_moisture\": {\n" +
        "\t\t\t\t\"id\": 2, \n" +
        "\t\t\t\t\"channel\": \"base1/sensor2/soil_moisture\"\n" +
        "\t\t\t}, \n" +
        "\t\t\t\"name\": \"Фикус\", \n" +
        "\t\t\t\"soil_moisture_id\": 2, \n" +
        "\t\t\t\"id\": 1, \n" +
        "\t\t\t\"water_level\": {\n" +
        "\t\t\t\t\"id\": 1, \n" +
        "\t\t\t\t\"channel\": \"base1/sensor1/water_level\"\n" +
        "\t\t\t}, \n" +
        "\t\t\t\"water_level_id\": 1\n" +
        "\t\t}, \n" +
        "\t\t{\n" +
        "\t\t\t\"pump_state_id\": 8, \n" +
        "\t\t\t\"pump_state\": {\n" +
        "\t\t\t\t\"id\": 8, \n" +
        "\t\t\t\t\"channel\": \"base2/device1/pump_state\"\n" +
        "\t\t\t}, \n" +
        "\t\t\t\"soil_moisture\": {\n" +
        "\t\t\t\t\"id\": 7, \n" +
        "\t\t\t\t\"channel\": \"base2/sensor2/soil_moisture\"\n" +
        "\t\t\t}, \n" +
        "\t\t\t\"name\": \"Молодило\", \n" +
        "\t\t\t\"soil_moisture_id\": 7, \n" +
        "\t\t\t\"id\": 2, \n" +
        "\t\t\t\"water_level\": {\n" +
        "\t\t\t\t\"id\": 6, \n" +
        "\t\t\t\t\"channel\": \"base2/sensor1/water_level\"\n" +
        "\t\t\t}, \n" +
        "\t\t\t\"water_level_id\": 6\n" +
        "\t\t}, \n" +
        "\t\t{\n" +
        "\t\t\t\"pump_state_id\": 8, \n" +
        "\t\t\t\"pump_state\": {\n" +
        "\t\t\t\t\"id\": 8, \n" +
        "\t\t\t\t\"channel\": \"base2/device1/pump_state\"\n" +
        "\t\t\t}, \n" +
        "\t\t\t\"soil_moisture\":  {\n" +
        "\t\t\t\t\"id\": 9, \n" +
        "\t\t\t\t\"channel\": \"device1/humidity\"\n" +
        "\t\t\t}, \n" +
        "\t\t\t\"name\": \"тест\", \n" +
        "\t\t\t\"soil_moisture_id\": 9, \n" +
        "\t\t\t\"id\": 3, \n" +
        "\t\t\t\"water_level\": {\n" +
        "\t\t\t\t\"id\": 6, \n" +
        "\t\t\t\t\"channel\": \"base2/sensor1/water_level\"\n" +
        "\t\t\t}, \n" +
        "\t\t\t\"water_level_id\": 6\n" +
        "\t\t}\n" +
        "\t]\n" +
        "}"

const val MOCK_PLANT_INFO = "{\n" +
        "\t\"is_paginated\": false, \n" +
        "\t\"object_list\": [\n" +
        "\t\t{\n" +
        "\t\t\t\"channel_source\": \"base1/sensor1/water_level\", \n" +
        "\t\t\t\"value\": true, \n" +
        "\t\t\t\"time_received\": \"2020-07-10 08:42:58.113751+00:00\", \n" +
        "\t\t\t\"id\": 2094, \n" +
        "\t\t\t\"name\": \"Фикус\"\n" +
        "\t\t}, \n" +
        "\t\t{\n" +
        "\t\t\t\"channel_source\": \"base1/sensor2/soil_moisture\", \n" +
        "\t\t\t\"value\": 189.0, \n" +
        "\t\t\t\"time_received\": \"2020-07-09 06:07:41.059828+00:00\", \n" +
        "\t\t\t\"id\": 10, \n" +
        "\t\t\t\"name\": \"Фикус\"\n" +
        "\t\t}, \n" +
        "\t\t{\n" +
        "\t\t\t\"channel_source\": \"base1/device1/pump_state\", \n" +
        "\t\t\t\"value\": true, \n" +
        "\t\t\t\"time_received\": \"2020-07-10 07:46:29.164237+00:00\", \n" +
        "\t\t\t\"id\": 322, \n" +
        "\t\t\t\"name\": \"Фикус\"\n" +
        "\t\t}\n" +
        "\t], \n" +
        "\t\"pages\": 1, \n" +
        "\t\"count\": 3, \n" +
        "\t\"current\": 1\n" +
        "}"
const val MOCK_WATERING = "{\n" +
        "\t\"Response\": \"pump on\", \n" +
        "\t\"Plant_id\": \"1\"\n" +
        "}"
const val MOCK_SOIL_MOISTURE = "{\n" +
        "\t\"is_paginated\": false, \n" +
        "\t\"soilmoisture_list\": [\n" +
        "\t\t{\n" +
        "\t\t\t\"channel_source\": \"base1/sensor2/soil_moisture\", \n" +
        "\t\t\t\"value\": 189.0, \n" +
        "\t\t\t\"time_received\": \"2020-07-09 06:07:41.059828+00:00\", \n" +
        "\t\t\t\"id\": 10, \n" +
        "\t\t\t\"name\": \"Фикус\"\n" +
        "\t\t}, \n" +
        "\t\t{\n" +
        "\t\t\t\"channel_source\": \"base1/sensor2/soil_moisture\", \n" +
        "\t\t\t\"value\": 172.0, \n" +
        "\t\t\t\"time_received\": \"2020-07-08 13:48:37.467564+00:00\", \n" +
        "\t\t\t\"id\": 9, \n" +
        "\t\t\t\"name\": \"Фикус\"\n" +
        "\t\t}\n" +
        "\t], \n" +
        "\t\"pages\": 1, \n" +
        "\t\"count\": 2, \n" +
        "\t\"current\": 1\n" +
        "}"
const val MOCK_WATER_LEVEL = "{\n" +
        "\t\"is_paginated\": true, \n" +
        "\t\"waterlevel_list\": [\n" +
        "\t\t{\n" +
        "\t\t\t\"channel_source\": \"base1/sensor1/water_level\", \n" +
        "\t\t\t\"value\": true, \n" +
        "\t\t\t\"time_received\": \"2020-07-10 08:42:58.113751+00:00\", \n" +
        "\t\t\t\"id\": 2094, \n" +
        "\t\t\t\"name\": \"Фикус\"\n" +
        "\t\t}, \n" +
        "\t\t{\n" +
        "\t\t\t\"channel_source\": \"base1/sensor1/water_level\", \n" +
        "\t\t\t\"value\": true, \n" +
        "\t\t\t\"time_received\": \"2020-07-10 08:42:44.910192+00:00\", \n" +
        "\t\t\t\"id\": 2093, \n" +
        "\t\t\t\"name\": \"Фикус\"\n" +
        "\t\t}, \n" +
        "\t\t{\n" +
        "\t\t\t\"channel_source\": \"base1/sensor1/water_level\", \"value\": true, \"time_received\": \"2020-07-10 08:42:42.909995+00:00\", \"id\": 2092, \"name\": \"Фикус\"}, \n" +
        "\t\t{\"channel_source\": \"base1/sensor1/water_level\", \"value\": true, \"time_received\": \"2020-07-10 08:42:40.917963+00:00\", \"id\": 2091, \"name\": \"Фикус\"}, \n" +
        "\t\t{\"channel_source\": \"base1/sensor1/water_level\", \"value\": true, \"time_received\": \"2020-07-10 08:42:38.909971+00:00\", \"id\": 2090, \"name\": \"Фикус\"}, \n" +
        "\t\t{\"channel_source\": \"base1/sensor1/water_level\", \"value\": true, \"time_received\": \"2020-07-10 08:42:36.910129+00:00\", \"id\": 2089, \"name\": \"Фикус\"}, \n" +
        "\t\t{\"channel_source\": \"base1/sensor1/water_level\", \"value\": true, \"time_received\": \"2020-07-10 08:42:34.910197+00:00\", \"id\": 2088, \"name\": \"Фикус\"}, \n" +
        "\t\t{\"channel_source\": \"base1/sensor1/water_level\", \"value\": true, \"time_received\": \"2020-07-10 08:42:32.900145+00:00\", \"id\": 2087, \"name\": \"Фикус\"}, \n" +
        "\t\t{\"channel_source\": \"base1/sensor1/water_level\", \"value\": true, \"time_received\": \"2020-07-10 08:42:30.909949+00:00\", \"id\": 2086, \"name\": \"Фикус\"}, \n" +
        "\t\t{\"channel_source\": \"base1/sensor1/water_level\", \"value\": true, \"time_received\": \"2020-07-10 08:41:56.903130+00:00\", \"id\": 2085, \"name\": \"Фикус\"}\n" +
        "\t], \n" +
        "\t\"pages\": 72, \n" +
        "\t\"count\": 712, \n" +
        "\t\"current\": 1\n" +
        "}"
const val MOCK_PUMP_STATE = "{\n" +
        "\t\"is_paginated\": true, \n" +
        "\t\"pumpstate_list\": [\n" +
        "\t\t{\n" +
        "\t\t\t\"channel_source\": \"base1/device1/pump_state\", \n" +
        "\t\t\t\"value\": true, \n" +
        "\t\t\t\"time_received\": \"2020-07-10 07:46:29.164237+00:00\", \n" +
        "\t\t\t\"id\": 322, \n" +
        "\t\t\t\"name\": \"Фикус\"\n" +
        "\t\t}, \n" +
        "\t\t{\"channel_source\": \"base1/device1/pump_state\", \"value\": true, \"time_received\": \"2020-07-10 07:46:19.367046+00:00\", \"id\": 321, \"name\": \"Фикус\"}, \n" +
        "\t\t{\"channel_source\": \"base1/device1/pump_state\", \"value\": true, \"time_received\": \"2020-07-10 07:35:40.576068+00:00\", \"id\": 320, \"name\": \"Фикус\"}, \n" +
        "\t\t{\"channel_source\": \"base1/device1/pump_state\", \"value\": true, \"time_received\": \"2020-07-10 07:35:30.784818+00:00\", \"id\": 319, \"name\": \"Фикус\"}, \n" +
        "\t\t{\"channel_source\": \"base1/device1/pump_state\", \"value\": true, \"time_received\": \"2020-07-10 07:35:07.567677+00:00\", \"id\": 318, \"name\": \"Фикус\"}, \n" +
        "\t\t{\"channel_source\": \"base1/device1/pump_state\", \"value\": true, \"time_received\": \"2020-07-10 07:34:57.800618+00:00\", \"id\": 317, \"name\": \"Фикус\"}, \n" +
        "\t\t{\"channel_source\": \"base1/device1/pump_state\", \"value\": true, \"time_received\": \"2020-07-10 07:30:28.471317+00:00\", \"id\": 316, \"name\": \"Фикус\"}, \n" +
        "\t\t{\"channel_source\": \"base1/device1/pump_state\", \"value\": true, \"time_received\": \"2020-07-10 07:30:18.645746+00:00\", \"id\": 315, \"name\": \"Фикус\"}, \n" +
        "\t\t{\"channel_source\": \"base1/device1/pump_state\", \"value\": true, \"time_received\": \"2020-07-10 07:24:09.283770+00:00\", \"id\": 314, \"name\": \"Фикус\"}, \n" +
        "\t\t{\"channel_source\": \"base1/device1/pump_state\", \"value\": true, \"time_received\": \"2020-07-10 07:23:59.463755+00:00\", \"id\": 313, \"name\": \"Фикус\"}\n" +
        "\t], \n" +
        "\t\"pages\": 3, \n" +
        "\t\"count\": 22, \n" +
        "\t\"current\": 1\n" +
        "}"