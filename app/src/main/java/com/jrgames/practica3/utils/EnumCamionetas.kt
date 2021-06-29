package com.jrgames.practica3.utils

import com.jrgames.practica3.R

enum class EnumCamionetas {

    CAM1,CAM2,CAM3,CAM4, CAM5,CAM6,CAM7; //dECLARAMOS LAS CAMIONETAS DEL ENUM

    companion object{

        //Inicializar el enum

        fun getFromId(idCamioneta: Int):EnumCamionetas{

            when(idCamioneta){

                1->{
                    return CAM1
                }

                2->{
                    return CAM2
                }

                3->{
                    return CAM3
                }

                4->{
                    return CAM4
                }
                5->{
                    return CAM5
                }
                6->{
                    return CAM6
                }
                7->{
                    return CAM7
                }
                else ->{
                    return CAM2 //Default en este caso la Camioneta
                }
            }

        }


        /**
         * Obtener el asset
         */
        fun getRecurso(enumCamioneta: EnumCamionetas): Int{

            when(enumCamioneta){

                CAM1 ->{
                    return R.drawable.ic_cam1
                }
                CAM2  ->{
                    return R.drawable.ic_cam2
                }
                CAM3 ->{
                    return R.drawable.ic_cam3
                }
                CAM4 ->{
                    return R.drawable.ic_cam4
                }
                CAM5 ->{
                    return R.drawable.ic_cam5
                }
                CAM6 ->{
                    return R.drawable.ic_cam6
                }CAM7->{
                    return R.drawable.ic_cam1
                }else ->{
                    return R.drawable.ic_cam3
                }
            }
        }
    }
}