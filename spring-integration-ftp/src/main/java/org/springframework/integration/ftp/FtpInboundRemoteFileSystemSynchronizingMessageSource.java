package org.springframework.integration.ftp;

import org.apache.commons.net.ftp.FTPFile;
import org.springframework.integration.file.AbstractInboundRemoteFileSystemSynchronizingMessageSource;


/**
 * a {@link org.springframework.integration.core.MessageSource} implementation for FTP
 *
 * @author Josh Long
 */
public class FtpInboundRemoteFileSystemSynchronizingMessageSource
		extends AbstractInboundRemoteFileSystemSynchronizingMessageSource<FTPFile, FtpInboundRemoteFileSystemSynchronizer> {
	private volatile FtpClientPool clientPool;

	public void setClientPool(FtpClientPool clientPool) {
		this.clientPool = clientPool;
	}

	@Override
	protected void doStart() {
		this.synchronizer.start();
	}

	@Override
	protected void doStop() {
		this.synchronizer.stop();
	}

	@Override
	protected void onInit() {
		super.onInit();
		this.synchronizer.setClientPool(this.clientPool);
	}

	public String getComponentType() {
		return "ftp:inbound-channel-adapter";
	}
}