/*
 * This file is part of VLCJ.
 *
 * VLCJ is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * VLCJ is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with VLCJ.  If not, see <http://www.gnu.org/licenses/>.
 *
 * Copyright 2015 Caprica Software Limited.
 */

package uk.co.caprica.vlcjplayer.view.action.mediaplayer;

import uk.co.caprica.vlcjplayer.view.action.Resource;

import java.awt.event.ActionEvent;

import party.Client;

import static uk.co.caprica.vlcjplayer.Application.application;

public final class PlayAction extends MediaPlayerAction {
	
	//---------------------NUEVO----------------------
	private Client partyClient;
	//---------------------/NUEVO----------------------
	
	public void setPartyClient(Client pc) {
		this.partyClient = pc;
	}

    PlayAction(Resource resource) {
        super(resource);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!application().mediaPlayer().status().isPlaying()) {
            application().mediaPlayer().controls().play();
          //---------------------NUEVO----------------------
            if(partyClient != null) {
            	partyClient.play();
            }
          //---------------------/NUEVO----------------------
        }
        else {
            application().mediaPlayer().controls().pause();
          //---------------------NUEVO----------------------
            if(partyClient != null) {
            	partyClient.pause();
            }
          //---------------------/NUEVO----------------------
        }
    }

}
