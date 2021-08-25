package com.goodsoft.prisson.main

import android.graphics.BlendMode
import android.graphics.BlendModeColorFilter
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.ColorInt
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.goodsoft.prisson.R
import com.goodsoft.prisson.api.response.FightResponse
import com.goodsoft.prisson.api.response.Player
import com.goodsoft.prisson.main.fight.FightViewModel
import com.goodsoft.prisson.utils.DataState
import com.goodsoft.prisson.utils.Pref
import com.goodsoft.prisson.utils.gone
import com.goodsoft.prisson.utils.show
import kotlinx.android.synthetic.main.layout_main_activity.*


class MainActivity : AppCompatActivity(), View.OnClickListener {

    var viewModel: FightViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(FightViewModel::class.java)

        setContentView(R.layout.layout_main_activity)
        vzbodritsaButton.setOnClickListener(this)
        hitLegButton.setOnClickListener(this)
        handHitButton.setOnClickListener(this)
        contraHitButton.setOnClickListener(this)
        fingerHitButton.setOnClickListener(this)
        painHitButton.setOnClickListener(this)

        restartLuse.setOnClickListener(this)
        restartWin.setOnClickListener(this)

        enableButtons()
//        setContentView(MyView(this, getWindowManager().getDefaultDisplay()))

        viewModel?.state?.observe(this, Observer {
            when (it) {
                is DataState.Loading -> {
                }
                is DataState.Data -> {
                    val data = it.data
                    if (data is FightResponse) {
                        handleFight(data)
                    }
                }
            }
        })

        viewModel?.action?.observe(this, Observer {
            if (it) {
                onClick(handHitButton)
            }
        })

        viewModel?.resetKicks?.observe(this, Observer { energy ->
            myView.firstPlayer?.energy = energy
        })
        viewModel?.timeLiveData?.observe(this, Observer {
            myView.time = it.toInt()
        })


        val pref = Pref.getInstance(this)

//        pref?.saveLogin("Jim Smith")
//        pref?.saveLogin2("pidor")
        viewModel?.initUsers(pref?.getLogin() ?: "", pref?.getLogin2() ?: "")
    }


    private fun handleFight(fightResponse: FightResponse) {

        if (fightResponse.isAction && fightResponse.secondPlayer?.isMove == true) {
            viewModel?.isShow = false
        }

        when (fightResponse.status) {
            FightResponse.GameStatus.WIN -> {
                myView.gone()
                winView.show()
                viewModel?.stopTimer()
            }
            FightResponse.GameStatus.LUSE -> {
                myView.gone()
                gameOver.show()
                viewModel?.stopTimer()
            }
            FightResponse.GameStatus.FIGHT -> {

//                if(fightResponse.firstPlayer?.isEndRound == true || fightResponse.secondPlayer?.isEndRound == true){
//                    viewModel?.isShow = false
//                }

                if ((fightResponse.firstPlayer?.isMove == true && fightResponse.secondPlayer?.isMove == true && !fightResponse.isAction)) {
                    enableButtons()
                    viewModel?.endRound()
                    viewModel?.timer?.cancel()
                }

                if (fightResponse.firstPlayer?.isMove == true && fightResponse.secondPlayer?.isMove == true) {
                    viewModel?.isShow = false
                    viewModel?.timer?.cancel()
                    enableButtons()
                }

                if (viewModel?.isShow == false) {
                    when (fightResponse.secondPlayer?.kickType) {
                        Player.KickType.HEND -> {
                            Toast.makeText(this, "Противник уебал вас рукой", Toast.LENGTH_SHORT)
                                .show()
                        }
                        Player.KickType.LEG -> {
                            Toast.makeText(this, "Ногой в душу", Toast.LENGTH_SHORT).show()
                        }
                        Player.KickType.FINGER_IN_IES -> {
                            Toast.makeText(
                                this,
                                "Пальцем в глаз, не дал восстановить енергию",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                        Player.KickType.KONTR_ATTACK -> {
                            Toast.makeText(this, "Контр атака, не прошел урон по противнику", Toast.LENGTH_SHORT)
                                .show()
                        }
                        Player.KickType.PAIN_KICK -> {
                            Toast.makeText(this, "На болевой!", Toast.LENGTH_SHORT).show()
                        }
                        Player.KickType.HEALS -> {
                            Toast.makeText(this, "Похилился падла!", Toast.LENGTH_SHORT).show()
                        }
                        null -> {
                        }
                    }
                    viewModel?.isShow = true
                }

                myView.firstPlayer = fightResponse.firstPlayer
                myView.secondPlayer = fightResponse.secondPlayer
            }
        }

    }


    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.vzbodritsaButton -> {
                viewModel?.setAction(Player.KickType.HEALS)

                myView.setClick()
                disableButtons()
                accentButton(vzbodritsaButton)
            }
            R.id.handHitButton -> {
                viewModel?.setAction(Player.KickType.HEND)

                myView.setClick()
                disableButtons()
                accentButton(handHitButton)
            }
            R.id.contraHitButton -> {
                viewModel?.setAction(Player.KickType.KONTR_ATTACK)
                myView.setClick()
                disableButtons()
                accentButton(contraHitButton)
            }
            R.id.fingerHitButton -> {
                viewModel?.setAction(Player.KickType.FINGER_IN_IES)
                myView.setClick()
                disableButtons()
                accentButton(fingerHitButton)
            }
            R.id.painHitButton -> {
                viewModel?.setAction(Player.KickType.PAIN_KICK)
                myView.setClick()
                disableButtons()
                accentButton(painHitButton)
                viewModel?.getStatus()
            }
            R.id.hitLegButton -> {
                viewModel?.setAction(Player.KickType.LEG)
                myView.setClick()
                disableButtons()
                accentButton(hitLegButton)
            }
            R.id.refrashAllHitsButton -> {
                viewModel?.resetKicks()
            }
            R.id.restartWin -> {
                viewModel?.startFight()
                myView.show()
                winView.gone()
            }
            R.id.restartLuse -> {
                viewModel?.startFight()
                myView.show()
                gameOver.gone()
            }

        }
    }

    private fun accentButton(button: View) {
        button?.background = getDrawable(R.color.colorAccent)
    }

    private fun disableButtons() {
        vzbodritsaButton.background = getDrawable(R.color.disableFightButtons)
        handHitButton.background = getDrawable(R.color.disableFightButtons)
        contraHitButton.background = getDrawable(R.color.disableFightButtons)
        fingerHitButton.background = getDrawable(R.color.disableFightButtons)
        painHitButton.background = getDrawable(R.color.disableFightButtons)
        hitLegButton.background = getDrawable(R.color.disableFightButtons)
//        setEnableButtons(false)
    }

    private fun enableButtons() {
        vzbodritsaButton.background = getDrawable(R.color.fightButtons)
        handHitButton.background = getDrawable(R.color.fightButtons)
        contraHitButton.background = getDrawable(R.color.fightButtons)
        fingerHitButton.background = getDrawable(R.color.fightButtons)
        painHitButton.background = getDrawable(R.color.fightButtons)
        hitLegButton.background = getDrawable(R.color.fightButtons)

//        setEnableButtons(true)
    }

    fun setEnableButtons(state: Boolean) {
        vzbodritsaButton.isClickable = state
        handHitButton.isClickable = state
        contraHitButton.isClickable = state
        fingerHitButton.isClickable = state
        painHitButton.isClickable = state
        hitLegButton.isClickable = state
    }

    private fun Drawable.setColorFilter(
        @ColorInt color: Int
    ) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            colorFilter = BlendModeColorFilter(color, BlendMode.SRC_ATOP)
        } else {
            setColorFilter(color, PorterDuff.Mode.SRC_ATOP)
        }
    }

}